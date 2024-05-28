package lb;


import servers.Server;

public class LinkedList {
    private Node head;

    public LinkedList() {
    }

    public void add(Server server){
        Node current = head;
        if (current == null)
            head = new Node(server);
        else {
            while (current.next != null)
                current = current.next;
            current.next = new Node(server);
        }
    }

    public Node getHead(){
        return head;
    }

    public static class Node{

        private final Server server;
        private Node next;

        public Node(Server server) {
            this.server = server;
            this.next = null;
        }

        public Server getServer() {
            return server;
        }

        public Node getNext() {
            return next;
        }
    }

}
