package com.ps.application.data;

import com.ps.application.models.Class;

import java.util.List;

public interface ClassDao {
    Class getClassById (int classId);
    List<Class> getAllClasses();
    Class createClass (Class clas);
    Class updateClass (int classID, Class clas);
    Class deleteClass (int classID);



}
