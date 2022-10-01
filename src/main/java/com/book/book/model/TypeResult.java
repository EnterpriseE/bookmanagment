package com.book.book.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class TypeResult {

    private long id;
    //  private String uuid;
    private String type_name;

    private long parentId;

    public long getId() {
        return id;
    }

    public String getType_name() {
        return type_name;
    }

    public TypeResult(long id, String type_name, long parentId) {
        this.id = id;
        this.type_name = type_name;
        this.parentId = parentId;
    }

    public Type toType(){
        Type type = new Type();
        type.setId(this.id);
        type.setParentId(this.parentId);
        type.setType_name(this.type_name);

        return type;
    }
}

