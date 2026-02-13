public class TopSecret {
    public static void main(String[] args) {
        ProgramControl control = new ProgramControl();
        UserInterface ui = new UserInterface(control);
        ui.run(args);
    }
}
