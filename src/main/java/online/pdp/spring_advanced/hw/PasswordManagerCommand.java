package online.pdp.spring_advanced.hw;

import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class PasswordManagerCommand {

    private String password;
    private boolean isValidPassword = true;


    @ShellMethod(value = "Add password", key = "add-pswd")
    public void addPassword(
            @ShellOption(value = "-p", help = "Create password here") String password
    ) {
        this.password = password;
    }

    @ShellMethod(value = "Get password", key = "get-pswd")
    public String getPassword() {
        return this.password;
    }

    @ShellMethod(value = "Create password with lenght", key = "create-pswd-len")
    public void createPasswordWithLength(
            @ShellOption(value = "-l") Integer length,
            @ShellOption(value = "-p") String password
    ) {
        if (password.length() != length) {
            isValidPassword = false;
            this.password = null;
        } else {
            this.password = password;
        }
    }

    @ShellMethodAvailability({"createPasswordWithLength"})
    public Availability availabilityForLogin() {
        return isValidPassword ? Availability.available() : Availability.unavailable(" => Password invalid");
    }

}
