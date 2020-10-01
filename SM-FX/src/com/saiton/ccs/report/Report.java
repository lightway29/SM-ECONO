package com.saiton.ccs.report;

import javafx.beans.property.SimpleStringProperty;

public class Report {
    private final SimpleStringProperty rid;
    private final SimpleStringProperty name;
    private final SimpleStringProperty type;

    public Report() {
        rid = new SimpleStringProperty();
        name = new SimpleStringProperty();
        type = new SimpleStringProperty();
    }

    public SimpleStringProperty ridProperty() {
        return rid;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getRid() {
        return rid.get();
    }

    public String getName() {
        return name.get();
    }

    public String getType() {
        return type.get();
    }


    public void setRid(String rid) {
        this.rid.set(rid);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setType(String type) {
        this.type.set(type);
    }


    public void setFromStringArray(String[] data) {
        if (data == null) {
            return;
        }
        setRid(data[0]);
        setName(data[1]);
        setType(data[2]);
    }
}
