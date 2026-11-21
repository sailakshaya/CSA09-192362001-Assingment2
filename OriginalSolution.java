public class OriginalSolution {
    public static String buildTicket(String[] fields) {
        String ticket = "";
        for (String field : fields) {
            ticket = ticket + field;
        }
        return ticket;
    }

    public static void main(String[] args) {
        String[] fields = {"PNR=12345;", "Name=Arun;", "Coach=S1;", "Berth=24;"};
        System.out.println(buildTicket(fields));
    }
}
