package fr.rouen.mastergil.tptest;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BankAccountWithDAOUnitTest {
    @Mock
    Connection connectionMock;

    @Mock
    private JdbcDAO bankDAOMock;

    @InjectMocks
    private BankAccountWithDAO BaDAO;

    @InjectMocks
    private BankAccountWithDAO bankAccountTemplate;

    // TESTS

    @Test
    public void shouldsetUpConnectionOnce() throws SQLException, ConnectException {
        // GIVEN
        Mockito.when(connectionMock.isClosed()).thenReturn(false);
        Mockito.when(connectionMock.isReadOnly()).thenReturn(false);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        // WHEN
        assertDoesNotThrow(() -> {
            bankAccountTemplate.creerCompte();
        });
        // THEN
        Mockito.verify(bankDAOMock).setUpConnection();
    }

    @Test
    public void shouldThrowExceptionWhenClosed() throws SQLException {
        // GIVEN
        Mockito.when(connectionMock.isClosed()).thenReturn(true);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        // WHEN
        Exception e;
        e = assertThrows(ConnectException.class, () -> {
            bankAccountTemplate.creerCompte();
        });
        // THEN
        assertThat(e.getMessage().equals("Impossible d'avoir accès à la base de données"));
    }

    @Test
    public void shouldThrowExceptionWhenReadyOnly() throws SQLException {
        // GIVEN
        Mockito.when(connectionMock.isReadOnly()).thenReturn(true);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        // WHEN
        Exception e;
        e = assertThrows(ConnectException.class, () -> {
            bankAccountTemplate.creerCompte();
        });
        // THEN
        assertThat(e.getMessage().equals("Impossible d'avoir accès à la base de données"));
    }

    @Test
    public void shouldCreateDefaultAccount() throws SQLException, ConnectException {
        // GIVEN
        Mockito.when(connectionMock.isReadOnly()).thenReturn(false);
        Mockito.when(connectionMock.isClosed()).thenReturn(false);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        Mockito.when(bankDAOMock.getSolde()).thenReturn(new Money());
        // WHEN
        bankAccountTemplate.creerCompte();
        // THEN
        assertThat(bankAccountTemplate.consulterSolde()).endsWith("0 EURO");

    }

    @Test
    public void createAccountWithParam() throws SQLException, ConnectException {
        // GIVEN
        Mockito.when(connectionMock.isReadOnly()).thenReturn(false);
        Mockito.when(connectionMock.isClosed()).thenReturn(false);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        Mockito.when(bankDAOMock.getSolde()).thenReturn(new Money(12, Devise.DOLLAR));
        // WHEN
        bankAccountTemplate.creerCompte(12, Devise.DOLLAR);
        // THEN
        assertThat(bankAccountTemplate.consulterSolde()).endsWith("12 DOLLAR");
    }

    @Test
    public void shouldSoldeBePositive() throws SQLException, ConnectException {
        // GIVEN
        Mockito.when(connectionMock.isReadOnly()).thenReturn(false);
        Mockito.when(connectionMock.isClosed()).thenReturn(false);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        Mockito.when(bankDAOMock.getSolde()).thenReturn(new Money(10, Devise.EURO));
        // WHEN
        boolean isPositif = bankAccountTemplate.isSoldePositif();
        // THEN
        assertThat(isPositif).isTrue();
    }

    @Test
    public void shouldNotBePositive() throws SQLException, ConnectException {
        // GIVEN
        Mockito.when(connectionMock.isReadOnly()).thenReturn(false);
        Mockito.when(connectionMock.isClosed()).thenReturn(false);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        Mockito.when(bankDAOMock.getSolde()).thenReturn(new Money(-1, Devise.EURO));
        // WHEN
        boolean isPositif = bankAccountTemplate.isSoldePositif();
        // THEN
        assertThat(isPositif).isFalse();
    }

    @Test
    public void shouldRetrieveMoneyFromAccount() throws SQLException, ConnectException {
        // GIVEN
        Mockito.when(connectionMock.isReadOnly()).thenReturn(false);
        Mockito.when(connectionMock.isClosed()).thenReturn(false);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        Mockito.when(bankDAOMock.getSolde()).thenReturn(new Money(10, Devise.EURO));
        // WHEN
        bankAccountTemplate.retirerArgent(5);
        // THEN
        assertThat(bankAccountTemplate.consulterSolde()).endsWith("5 EURO");
    }

    @Test
    public void shouldDepositMoneyToAccount() throws SQLException, ConnectException {
        // GIVEN
        Mockito.when(connectionMock.isReadOnly()).thenReturn(false);
        Mockito.when(connectionMock.isClosed()).thenReturn(false);
        Mockito.when(bankDAOMock.setUpConnection()).thenReturn(connectionMock);
        Mockito.when(bankDAOMock.getSolde()).thenReturn(new Money(0, Devise.EURO));
        // WHEN
        bankAccountTemplate.deposerArgent(5);
        // THEN
        assertThat(bankAccountTemplate.consulterSolde()).endsWith("5 EURO");
    }

}
