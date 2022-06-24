package domain;

public class ResultInfo {
    private boolean flag;
    private String errorMsg;

    public boolean isFlag() {
        return flag;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


}
