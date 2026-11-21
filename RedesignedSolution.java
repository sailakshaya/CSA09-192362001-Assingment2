public class RedesignedSolution {
    public static String buildTicket(String[] fields) {
        StringBuilder ticket = new StringBuilder();
        for (String field : fields) {
            ticket.append(field);
        }
        return ticket.toString();
    }

    public static void main(String[] args) {
        String[] fields = {"PNR=12345;", "Name=Arun;", "Coach=S1;", "Berth=24;"};
        System.out.println(buildTicket(fields));
    }
}
