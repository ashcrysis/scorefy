package estacio.acad.mobplacar.model;

public class Log {
    private String User;
    private String date;
    private String preview;
    private String Horario;
    private String Sala;

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getSala() {
        return Sala;
    }

    public void setSala(String sala) {
        Sala = sala;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public static class LogBuilder{

        private String User;
        private String date;
        private String preview;

        private String Horario;
        private String Sala;


        public LogBuilder setHorario(String horario) {
            this.Horario = horario;
            return this;
        }

        public LogBuilder setSala(String sala) {
            this.Sala = sala;
            return this;
        }

        public LogBuilder setUser(String user) {
            User = user;
            return this;
        }

        public LogBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public LogBuilder setPreview(String preview) {
            this.preview = preview;
            return this;
        }

        private LogBuilder(){}
        public static LogBuilder builder(){
            return new LogBuilder();

        }

        public Log build(){

            Log log = new Log();
        log.User = User;
        log.date = date;
        log.preview = preview;
        log.Sala = Sala;
        log.Horario = Horario;
        return log;
        }

    }
}
