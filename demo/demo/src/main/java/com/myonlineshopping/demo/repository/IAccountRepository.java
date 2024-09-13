package com.myonlineshopping.demo.repository;

import com.myonlineshopping.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account,Long> {
    public List<Account> findByOwnerId(Long id); //esto por nomenclatura
    public void deleteByOwnerId(Long customer); //esto por nomenclatura
    @Modifying
    @Query("UPDATE Account a SET a.balance = a.balance + :cantidad WHERE a.id = :cuentaId AND a.owner.id = :propietario")
    public void addMoney(@Param("cuentaId") Long cuentaId, @Param("propietario") Long customer, @Param("cantidad") Integer cantidad);
    @Modifying
    @Query("UPDATE Account a SET a.balance = a.balance - :cantidad WHERE a.id = :cuentaId AND a.owner.id = :propietario")
    public void withdrawMoney(@Param("cuentaId") Long cuentaId,@Param("propietario") Long customer,@Param("cantidad") Integer cantidad) ;
    /*
    -> Listar todas las cuentas y una cuenta de manera individual. findAll. findById
	-> Listar las cuentas de un usuario.
	-> Crear, actualizar, borrar cuentas.
	-> Añadir dinero al balance, indicando cuenta, cantidad y propietario.
	-> Hacer un retiro, indicando cuenta, cantidad y propietario.
	-> Borrar todas las cuentas de un usuario*/
}
