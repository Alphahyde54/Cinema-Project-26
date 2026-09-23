public class booking {
    String forNavn;
    String efterNavn;
    String email;


    public booking(String forNavn, String efterNavn, String email){
        this.forNavn = forNavn;
        this.efterNavn = efterNavn;
        this.email = email;
    }

    public String toString(String bookedSeat){
        return String.format("""
                Navn: %s %s
                Email: %s
                Billetter: %s
                """, forNavn, efterNavn, email, bookedSeat);
    }
}
