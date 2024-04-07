package security;

public interface Authentification
{
    boolean login(String email, String parola);
    void register(String nume, String email, String parola);
}
