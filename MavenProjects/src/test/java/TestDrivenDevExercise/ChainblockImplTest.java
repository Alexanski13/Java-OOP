package TestDrivenDevExercise;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    private Chainblock chainblock;
    private List<Transaction> transactions;

    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        this.prepareTransactions();
    }

    private void fillChainBlockWithTransactions() {
        transactions.forEach(transaction -> chainblock.add(transaction));
    }

    private void prepareTransactions() {
        Transaction transaction = new TransactionImpl(0, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 11.20);
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Tqnko", 10);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.UNAUTHORIZED, "Tqnko", "Sasho", 11.0);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.FAILED, "Pesho", "Richard", 12.20);
        Transaction transaction4 = new TransactionImpl(4, TransactionStatus.SUCCESSFUL, "Richard", "Sasho", 10.50);
        Transaction transaction5 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Benjamin", "Sasho", 14);
        Transaction transaction6 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Frankenstein", "Sasho", 9);
        transactions.add(transaction);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        transactions.add(transaction6);
    }

    @Test
    public void testAddShouldAddTransaction() {
        chainblock.add(transactions.get(0));
        assertEquals(1, chainblock.getCount());
        chainblock.add(transactions.get(1));
        assertEquals(2, chainblock.getCount());
    }

    @Test
    public void testShouldNotAddDuplicateTransaction() {
        chainblock.add(transactions.get(0));
        chainblock.add(transactions.get(0));
        assertEquals(1, chainblock.getCount());
    }

    @Test
    public void testContainsWithTransactionShouldReturnFalse() {
        this.chainblock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainblock.contains(transactions.get(1));
        assertFalse(chainBlockContainsTransaction);
    }

    @Test
    public void testContainsWithTransactionShouldReturnTrue() {
        this.chainblock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainblock.contains(transactions.get(0));
        assertTrue(chainBlockContainsTransaction);
    }

    @Test
    public void testContainsWithIDShouldReturnFalse() {
        this.chainblock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainblock.contains(transactions.get(1).getId());
        assertFalse(chainBlockContainsTransaction);
    }

    @Test
    public void testContainsWithIDShouldReturnTrue() {
        this.chainblock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainblock.contains(transactions.get(0).getId());
        assertTrue(chainBlockContainsTransaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusShouldThrowForMissingTransacton() {
        chainblock.changeTransactionStatus(100, TransactionStatus.FAILED);
    }

    @Test
    public void testChangeTransactionStatusShouldChangeStatus() {
        chainblock.add(transactions.get(0));

        chainblock.changeTransactionStatus(transactions.get(0).getId(), TransactionStatus.FAILED);

        TransactionStatus transactionStatus = chainblock.getById(transactions.get(0).getId()).getStatus();

        assertEquals(TransactionStatus.FAILED, transactionStatus);
    }

    @Test
    public void testGetByIDShouldReturnTransaction() {
        chainblock.add(transactions.get(0));

        Transaction actual = chainblock.getById(transactions.get(0).getId());

        assertEquals(transactions.get(0), actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIDShouldThrowForMissingTransaction() {
        chainblock.add(transactions.get(0));
        chainblock.add(transactions.get(1));
        chainblock.add(transactions.get(2));

        chainblock.getById(420);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransactionByIDShouldThrowForMissingTransaction() {
        fillChainBlockWithTransactions();

        chainblock.removeTransactionById(420);
    }

    @Test
    public void testTransactionByIDShouldRemoveIt() {
        fillChainBlockWithTransactions();

        chainblock.removeTransactionById(1);


        assertFalse(chainblock.contains(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusShouldThrowIfNoSuchTransactions() {
        fillChainBlockWithTransactions();
        chainblock.getByTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetByTransactionStatusShouldReturnTransactions() {
        fillChainBlockWithTransactions();
        var expectedTransactions = this.transactions.stream()
                .filter(transaction -> transaction.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusShouldThrowIfNoTransactions() {
        fillChainBlockWithTransactions();
        chainblock.getAllSendersWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllSendersWithTransactionStatusShouldReturnSortedNames() {
        fillChainBlockWithTransactions();
        var expectedTransactionSenders = transactions.stream().filter(transaction -> transaction.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getFrom)
                .collect(Collectors.toList());

        Iterable<String> actualTransactionSenders = chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        assertEquals(expectedTransactionSenders, actualTransactionSenders);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusShouldThrowIfNoTransactions() {
        fillChainBlockWithTransactions();
        chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllReceiversWithTransactionStatusShouldReturnSoretedNames() {
        fillChainBlockWithTransactions();
        var expectedTransactionReceivers = transactions.stream().filter(transaction -> transaction.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getTo)
                .collect(Collectors.toList());

        Iterable<String> actualTransactionReceivers = chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
    }

    @Test
    public void testGetAllInAmountRangeShouldReturnTransactions() {
        fillChainBlockWithTransactions();
        var expectedTransactions = transactions.stream()
                .filter(transaction -> transaction.getAmount() < 12 && transaction.getAmount() > 10)
                .collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = chainblock.getAllInAmountRange(10, 12);

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void testGetAllInAmountRangeShouldReturnEmptyCollectionIfNoSuchTransactions() {
        fillChainBlockWithTransactions();
        var expectedTransactions = transactions.stream()
                .filter(transaction -> transaction.getAmount() < 420 && transaction.getAmount() > 69)
                .collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = chainblock.getAllInAmountRange(69, 420);

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeShouldThrowForNoSuchReceiver() {
        fillChainBlockWithTransactions();
        chainblock.getByReceiverAndAmountRange("Ivancho", 10, 12);
    }

    @Test
    public void testGetByReceiverAndAmountRangeShouldReturnTransactionsInRangeByReceiverSortedByAmount() {
        fillChainBlockWithTransactions();
        var expectedTransactions = transactions.stream()
                .filter(transaction -> transaction.getAmount() < 13 && transaction.getAmount() > 10)
                .filter(transaction -> transaction.getTo().equals("Sasho"))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = chainblock.getByReceiverAndAmountRange("Sasho", 10, 13);

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void testGetAllOrderedByAmountDescendingThenByIDShouldReturnTransactions() {
        fillChainBlockWithTransactions();
        var expectedTransactions = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = chainblock.getAllOrderedByAmountDescendingThenById();

        assertEquals(expectedTransactions, actualTransactions);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverOrderedByAmountThenByIdShouldThrowForNoSuchTransactions() {
        fillChainBlockWithTransactions();

        chainblock.getByReceiverOrderedByAmountThenById("Ivan");
    }

    @Test
    public void testGetByReceiverOrderedByAmountThenByIdShouldReturnTransactions() {
        fillChainBlockWithTransactions();

        var expectedTransactions = transactions.stream()
                .filter(transaction -> transaction.getTo().equals("Sasho"))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        var actualTransactions = chainblock.getByReceiverOrderedByAmountThenById("Sasho");

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingShouldThrow() {
        fillChainBlockWithTransactions();

        chainblock.getBySenderOrderedByAmountDescending("Alien");
    }

    @Test
    public void testGetBySenderOrderedByAmountDescendingShouldReturn() {
        fillChainBlockWithTransactions();

        var expectedTransactions = transactions.stream().filter(transaction -> transaction.getFrom().equals("Pesho"))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        var actualTransactions = chainblock.getBySenderOrderedByAmountDescending("Pesho");

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmount() {
        fillChainBlockWithTransactions();

        var expectedTransactions = transactions.stream()
                .filter(transaction -> transaction.getStatus().equals(TransactionStatus.SUCCESSFUL) && transaction.getAmount() < 11).collect(Collectors.toList());

        var actual = chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 11);

        assertEquals(expectedTransactions, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingShouldThrow() {
        fillChainBlockWithTransactions();
        chainblock.getBySenderAndMinimumAmountDescending("Alien", 42069);
    }
    @Test
    public void testGetBySenderAndMinimumAmountDescendingShouldReturn() {
        fillChainBlockWithTransactions();

        var expected = transactions.stream()
                .filter(transaction -> transaction.getFrom().equals("Pesho") && transaction.getAmount() > 10)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());


        var actual = chainblock.getBySenderAndMinimumAmountDescending("Pesho", 10);

        assertEquals(expected, actual);
    }
}