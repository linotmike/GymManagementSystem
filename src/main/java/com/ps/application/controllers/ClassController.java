package com.ps.application.controllers;

import com.ps.application.data.ClassDao;
import com.ps.application.models.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/classes")
public class ClassController {

    private ClassDao classDao;

    @Autowired
    public ClassController(ClassDao classDao){
        this.classDao = classDao;
    }

    @GetMapping
    public ResponseEntity<?> getAllClasses (){
//        Class clas = classDao.getAllClasses()
        List<Class> clas = classDao.getAllClasses();
        return ResponseEntity.ok(clas);
    }

    @GetMapping("{classId}")
    public ResponseEntity<?> getClassesById (@PathVariable int classId){
//    Class clas = classDao.getClassById(classId);
        com.ps.application.models.Class clas = classDao.getClassById(classId);
        return ResponseEntity.ok(clas);
    }

    @PostMapping
    public ResponseEntity<?> createClass (@RequestBody Class clas){
        Class newClas= classDao.createClass(clas);
        return ResponseEntity.ok(newClas);
    }
    @PutMapping("{classId}")
    public ResponseEntity<?> updateClass (@PathVariable int classId, @RequestBody Class clas){
        Class updatedClas = classDao.updateClass(classId,clas);
        return ResponseEntity.ok(updatedClas);
    }
    @DeleteMapping("{classId}")
    public ResponseEntity <?> deleteClass (@PathVariable int classId){
        Class clas = classDao.deleteClass(classId);
        return ResponseEntity.ok(clas);
    }
}
