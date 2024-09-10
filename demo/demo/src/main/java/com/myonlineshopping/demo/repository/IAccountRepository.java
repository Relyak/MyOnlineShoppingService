package com.myonlineshopping.demo.repository;

import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IAccountRepository extends JpaRepository<Account,Long> {
    public List<Account> findByCustomer_id(Customer customer); //esto por nomenclatura
    public void deleteByCustomer(Customer customer); //esto por nomenclatura
    @Modifying
    @Query("UPDATE Account a SET a.balance = a.balance + :cantidad WHERE a.id = :cuentaId AND a.propietario = :propietario")
    public void addMoney(@Param("cuentaId") Long cuentaId, @Param("cantidad") int cantidad, @Param("propietario") Customer customer);
    @Modifying
    @Query("UPDATE Account a SET a.balance = a.balance - :cantidad WHERE a.id = :cuentaId AND a.propietario = :propietario")
    public void withdrawMoney(@Param("cuentaId") Long cuentaId, @Param("cantidad") int cantidad, @Param("propietario") Customer customer);
    /*
    -> Listar todas las cuentas y una cuenta de manera individual. findAll. findById
	-> Listar las cuentas de un usuario.
	-> Crear, actualizar, borrar cuentas.
	-> Añadir dinero al balance, indicando cuenta, cantidad y propietario.
	-> Hacer un retiro, indicando cuenta, cantidad y propietario.
	-> Borrar todas las cuentas de un usuario*/
}
