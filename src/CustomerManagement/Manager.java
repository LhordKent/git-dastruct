package CustomerManagement;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

class Manager {
    private Queue<Customer> generalQueue;
    private Stack<Customer> vipStack;
    private List<Customer> servedCustomers;

    public Manager() {
        generalQueue = new LinkedList<>();
        vipStack = new Stack<>();
        servedCustomers = new ArrayList<>();
    }

    public void addGeneralCustomer(String name) {
        generalQueue.add(new Customer(name, "General"));
    }

    public void addVipCustomer(String name) {
        vipStack.push(new Customer(name, "VIP"));
    }

    public String serveCustomer() {
        Customer servedCustomer;
        if (!vipStack.isEmpty()) {
            // Use an auxiliary stack to reverse the order
            Stack<Customer> auxStack = new Stack<>();
            while (!vipStack.isEmpty()) {
                auxStack.push(vipStack.pop());
            }
            servedCustomer = auxStack.pop();
            while (!auxStack.isEmpty()) {
                vipStack.push(auxStack.pop());
            }
        } else if (!generalQueue.isEmpty()) {
            servedCustomer = generalQueue.poll();
        } else {
            throw new IllegalStateException("No customers in the queue.");
        }
        servedCustomers.add(servedCustomer);
        return servedCustomer.getName();
    }

    public String getQueueStatus() {
        StringBuilder status = new StringBuilder("General Queue:\n");
        for (Customer customer : generalQueue) {
            status.append(customer.getName()).append(" - ").append(customer.getType()).append("\n");
        }

        status.append("\nVIP Stack:\n");
        for (Customer customer : vipStack) {
            status.append(customer.getName()).append(" - ").append(customer.getType()).append("\n");
        }

        return status.toString();
    }

    public List<Customer> getServedCustomers() {
        return servedCustomers;
    }
}