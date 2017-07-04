package br.com.grupojava.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
	    public static volatile SingularAttribute<User, Long> id;
	    public static volatile SingularAttribute<User, String> name;
	    public static volatile SingularAttribute<User, String> userName;
	    public static volatile SingularAttribute<User, String> password;
}
