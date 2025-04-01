
package CustomerManagement;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Manager {
    private Queue<Customer> generalQueue;
    private Stack<Customer> vipStack;

    public Manager() {
        generalQueue = new LinkedList<>();
        vipStack = new Stack<>();
    }

    public void addGeneralCustomer(String name) {
        generalQueue.add(new Customer(name, "General"));
    }

    public void addVipCustomer(String name) {
        vipStack.push(new Customer(name, "VIP"));
    }

    public String serveCustomer() {
        if (!vipStack.isEmpty()) {
            Customer servedCustomer = vipStack.pop();
            return servedCustomer.getName();
        } else if (!generalQueue.isEmpty()) {
            Customer servedCustomer = generalQueue.poll();
            return servedCustomer.getName();
        } else {
            throw new IllegalStateException("No customers in the queue.");
        }
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
}