package repository;

import entity.Account;

public interface AccountRepository {

	Account findByUsername(String username);
}
