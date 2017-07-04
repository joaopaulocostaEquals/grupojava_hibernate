package br.com.grupojava.app;



import java.util.List;
import java.util.Scanner;

import br.com.grupojava.dao.UserDao;
import br.com.grupojava.model.Conta;
import br.com.grupojava.model.User;

public class Principal {
	
	public static void novoUsuario(Scanner scanner){		
		User user = new User();
		
		System.out.println("----------------------------------");
		System.out.println("         Cadastro Usuario         ");
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
        user.setName(nome);
  
        System.out.print("Login: ");
		String login = scanner.nextLine();
        user.setUserName(login);
        
        System.out.print("Password: ");
		String senha = scanner.nextLine();
        user.setPassword(senha);
        UserDao dao = new UserDao();
        
        dao.addUser(user);
        dao.close();
        
	}
	
	public static void login(Scanner scanner){
		System.out.println("----------------------------------");
		 System.out.println("              Login              ");
        System.out.print("UserName: ");

		String login = scanner.nextLine();
		UserDao dao = new UserDao();
		User user = dao.getUserByLoginCriteria(login);
        dao.close();
		if(user != null){
	        System.out.print("Password: ");
			String senha = scanner.nextLine();
	        user.setPassword(senha);
	        
	        if(user.getPassword().equals(senha)){
	        	menuUsuario(scanner, user);
	        }else{
	        	System.out.println("Senha incorreta");
	        }
	        
		}
        
        
	}
	
	public static void menuUsuario(Scanner scanner, User user){
		int menu = 1;
		while(menu != 0){
			 System.out.println("----------------------------------");
			 System.out.println("            Menu Usuario          ");
			 System.out.println("1 - Criar conta");
			 System.out.println("2 - Listar contas");
			 System.out.println("0 - logout");
			 menu = Integer.parseInt(scanner.nextLine());
			 if(menu == 1){
				criarConta(scanner, user);
			 }else if( menu == 2){
				 if(user.getContas().size() == 0){
					 System.out.println("Nenhuma conta encontrada"); 
				 }
				 else{
					 for(Conta conta: user.getContas()){
						 System.out.println(conta.getId() + ": R$ " + conta.getSaldo());
					 }
				 }
			 }else{
				 UserDao dao = new UserDao();
				 dao.updateUser(user);
				 dao.close();
			 }
		}
	}
	
	public static void criarConta(Scanner scanner, User user){
		System.out.println("----------------------------------");
		System.out.println("             Nova Conta           ");
		Conta novaConta = new Conta();
		novaConta.setUsuario(user);
		user.getContas().add(novaConta);
		System.out.print("Saldo:");
		novaConta.setSaldo(Double.parseDouble(scanner.nextLine()));
	}
	
	
	public static void listarUsuarios(){
		System.out.println("----------------------------------");
		System.out.println("              Listar              ");
		UserDao dao = new UserDao();
		List<User> usuarios = dao.getAllUsersCriteria();
		for(User user: usuarios){
			System.out.println(user.getName() + ", " + user.getUserName());
		}
		dao.close();
	}
	
	

	public static void main(String[] args){
		
		
		Scanner scanner = new Scanner(System.in);
		
		int menu = 1;
		while(menu != 0){
			 System.out.println("----------------------------------");
			 System.out.println("            Menu Princiapl        ");
			 System.out.println("1 - Novo usuário");
			 System.out.println("2 - Login");
			 System.out.println("3 - Listar Usuarios");
			 System.out.println("0 - Sair");
			 menu = Integer.parseInt(scanner.nextLine());
			 
			 if(menu == 1){
				 novoUsuario(scanner);
			 }else if(menu == 2){
				 login(scanner);
			 }else if(menu == 3){	 
				 listarUsuarios();
			 }else{
				 System.out.println("Saindo");
			 }
		}
		
	}
}
