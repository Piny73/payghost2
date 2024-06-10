package it.tsp.boundary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import it.tsp.control.AccountStore;
import it.tsp.control.PayghostManager;
import it.tsp.control.RechargeStore;
import it.tsp.control.TransactionStore;
import it.tsp.dto.AccountSlice;
import it.tsp.dto.CreateRechargeDTO;
import it.tsp.dto.CreateTransactionDTO;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import it.tsp.entity.Transaction;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@RequestScoped
@Path("/accounts")
public class AccountsResource {

    @Inject
    AccountStore accountStore;

    @Inject
    RechargeStore rechargeStore;

    @Inject
    TransactionStore transactionStore;

    @Inject
    PayghostManager payghostManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response registration(@Valid Account account) {

        if (!Objects.equals(account.getPwd(), account.getConfirmPwd())) {
            throw new PayghostException("le password non corrispondono");
        }

        // account.setPwd(EncodeUtils.encode(account.getPwd()));

        Account saved = accountStore.saveAccount(account);

        if (account.getCredit().compareTo(BigDecimal.ZERO) > 0) {
            Recharge recharge = new Recharge(saved, account.getCredit());
            rechargeStore.saveRecharge(recharge);
        }
        return Response
                .status(Status.CREATED)
                .entity(saved.getId())
                .build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Account> result = accountStore.findAll();
        List<AccountSlice> convertedResult = result.stream()
                .map(v -> new AccountSlice(v.getId(), v.getFname(), v.getLname()))
                .collect(Collectors.toList());
        return Response.ok(convertedResult).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        Account account = accountStore.findAccountById(id)
                .orElseThrow(() -> new NotFoundException("account not exist"));
        return Response.ok(account).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(value = TxType.REQUIRES_NEW)
    @Path("/{id}/recharges")
    public Response doRecharge(@PathParam("id") long id, @Valid CreateRechargeDTO e) {
        Account account = accountStore.findAccountById(id)
                .orElseThrow(() -> new NotFoundException("account not exist"));
        Recharge toSave = new Recharge(account, e.amount());
        Recharge saved = rechargeStore.saveRecharge(toSave);
        account.increaseCredit(e.amount());
        accountStore.saveAccount(account);
        return Response.status(Status.CREATED)
                .entity(saved)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/recharges")
    public Response allRecharges(@PathParam("id") long id) {
        Account account = accountStore.findAccountById(id)
                .orElseThrow(() -> new NotFoundException("account not exist"));
        return Response.ok(rechargeStore.findRechargesByAccountId(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(value = TxType.REQUIRES_NEW)
    @Path("/{id}/transactions")
    public Response doTransaction(@PathParam("id") long id, @Valid CreateTransactionDTO e) {
        Account sender = accountStore.findAccountById(id)
                .orElseThrow(() -> new NotFoundException("sender not exist"));
        Account receiver = accountStore.findAccountById(e.receiverId())
                .orElseThrow(() -> new NotFoundException("receiver not exist"));

        Transaction tx = payghostManager.doTransaction(sender, receiver, e.amount());

        return Response.status(Status.CREATED)
                .entity(tx)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/transactions")
    public Response allTransactions(@PathParam("id") long id, @Valid CreateTransactionDTO e) {
        Account account = accountStore.findAccountById(id)
                .orElseThrow(() -> new NotFoundException("account not exist"));
        return Response.ok(transactionStore.findTransactionsByAccountId(id))
                .build();
    }
}