package com.example.scw.utils.factory;

import java.util.List;

public interface Factory<Child,Parent> {

    Child getOne(Parent parent);
    List<Child> getList(List<Parent> parents);
}
