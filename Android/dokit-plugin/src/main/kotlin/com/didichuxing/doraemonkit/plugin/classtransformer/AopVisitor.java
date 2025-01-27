package com.didichuxing.doraemonkit.plugin.classtransformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class AopVisitor extends ClassVisitor {
    private String className;

    public AopVisitor(int api) {
        super(api);
    }

    public AopVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        className=name;

        System.out.println("className:"+name);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
         MethodVisitor old = super.visitMethod(access, name, descriptor, signature, exceptions);
      if(!className.startsWith("com/babytree")){
          return old;
      }
        if(className.contains("AopVisitor")){
            return old;
        }
        if(className.contains("EncourageAop")){
            return old;
        }
        if(className.contains("MyLocalVariablesSorter")){
            return old;
        }
     if(className.contains("AopUtil")){
            return old;
        }

      return new MyLocalVariablesSorter(api,access,descriptor,old);


    }
}
