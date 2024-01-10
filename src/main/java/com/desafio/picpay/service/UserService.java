package com.desafio.picpay.service;


import com.desafio.picpay.model.TypeUser;
import com.desafio.picpay.model.Users;
import com.desafio.picpay.repositories.UserRepositorie;
import com.desafio.picpay.transactionException.NotFoundException;
import com.desafio.picpay.transactionException.ValidationStatusUserException;
import com.desafio.picpay.utils.ConversorMonetario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {
    private UserService userService;

    private ConversorMonetario conversorMonetario;


    @Autowired
    private UserRepositorie userRepositorie;


    public Users getUserById(Long userId) {
        Optional<Users> optionalUser = userRepositorie.findById(userId);
        return optionalUser.orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
    }


    public void validationStatusUser(Users sender, BigDecimal amount) throws Exception {

        if (sender.getType() == TypeUser.LOGISTAS) {
            throw new ValidationStatusUserException("Usuario não autorizado a realizar esse tipo de transação");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new ValidationStatusUserException("Saldo insulficiente para realizar esta transação");
        }

    }

}
