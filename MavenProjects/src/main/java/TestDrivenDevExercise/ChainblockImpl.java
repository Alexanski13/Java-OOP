package TestDrivenDevExercise;

import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private Map<Integer, Transaction> transactionMap;

    public ChainblockImpl() {
        this.transactionMap = new LinkedHashMap<>();
    }

    public int getCount() {
        return transactionMap.size();
    }

    public void add(Transaction transaction) {
        transactionMap.putIfAbsent(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return contains(transaction.getId());
    }

    public boolean contains(int id) {
        return transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        Transaction transaction = getById(id);
        transaction.setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        checkForTransactionID(id);
        transactionMap.remove(id);
    }

    public Transaction getById(int id) {
        checkForTransactionID(id);
        return transactionMap.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        var transactions = transactionMap.values().stream()
                .filter(transaction -> transaction.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if (transactions.size() == 0) {
            throw new IllegalArgumentException();
        }

        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> transactionByStatus = convertIterableToList(getByTransactionStatus(status));

        return transactionByStatus.stream()
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
    }

    private List<Transaction> convertIterableToList(Iterable<Transaction> iterable) {
        List<Transaction> list = new ArrayList<>();

        iterable.forEach(list::add);

        return list;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> transactionByStatus = convertIterableToList(getByTransactionStatus(status));

        return transactionByStatus.stream()
                .map(Transaction::getTo)
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return transactionMap.values().stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        var result = transactionMap.values()
                .stream()
                .filter(transaction -> transaction.getFrom().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if (result.size() == 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> transactionsSorted = convertIterableToList(getAllOrderedByAmountDescendingThenById());
        List<Transaction> result = transactionsSorted.stream()
                .filter(transaction -> transaction.getTo().equals(receiver)).collect(Collectors.toList());

        if (result.size() == 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return transactionMap.values()
                .stream()
                .filter(transaction -> transaction.getStatus().equals(status) && transaction.getAmount() < amount)
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        var result = transactionMap.values().stream()
                .filter(transaction -> transaction.getFrom().equals(sender) && transaction.getAmount() > amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if (result.size() == 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> transactionsByRange = convertIterableToList(getAllInAmountRange(lo, hi));
        var transactionsByRangeAndReceiver = transactionsByRange.stream()
                .filter(transaction -> transaction.getTo().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if (transactionsByRangeAndReceiver.size() == 0) {
            throw new IllegalArgumentException();
        }

        return transactionsByRangeAndReceiver;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return transactionMap.values().stream()
                .filter(transaction -> transaction.getAmount() < hi && transaction.getAmount() > lo)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return null;
    }

    private void checkForTransactionID(int id) {
        if (!transactionMap.containsKey(id)) {
            throw new IllegalArgumentException();
        }
    }
}
