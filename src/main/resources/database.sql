/*sqlite:

create table if not exists ACCOUNT
(
    ID    INTEGER PRIMARY KEY AUTOINCREMENT,
    BALANCE real not null
);

create table if not exists PLAYER
(
    ID        INTEGER PRIMARY KEY AUTOINCREMENT,
    CONNECTED_ACCOUNT int  not null
        references ACCOUNT,
    NAME              TEXT not null
);

create table if not exists TRANSACTIONLIST
(
    ID   INTEGER PRIMARY KEY AUTOINCREMENT,
    AMOUNT           real not null,
    TRANSACTION_TYPE TEXT not null,
    TRANSACTION_DATE TEXT not null,
    ACCOUNT_ID       int not null
        references ACCOUNT
);


INSERT INTO ACCOUNT (ID, BALANCE) VALUES (1, 50000.0 );
INSERT INTO PLAYER (ID, NAME, CONNECTED_ACCOUNT) VALUES (1, "George Malandrakis", 1);
INSERT INTO TRANSACTIONLIST(ID, AMOUNT, TRANSACTION_TYPE, TRANSACTION_DATE, ACCOUNT_ID) VALUES (1, 100.0, "DEBIT", "2020-06-03 11:18:05.123" ,1);
INSERT INTO TRANSACTIONLIST(ID, AMOUNT, TRANSACTION_TYPE, TRANSACTION_DATE, ACCOUNT_ID) VALUES (2, 100.0, "CREDIT", "2020-06-03 11:18:10.123" ,1);


mysql:

*/



CREATE SCHEMA if not exists `walletmicroservice` ;

create table if not exists walletmicroservice.WALLET
(
    ID    INTEGER PRIMARY KEY AUTO_INCREMENT,
    BALANCE real not null,
    NAME TEXT
);

create table if not exists walletmicroservice.PLAYER
(
    ID        INTEGER PRIMARY KEY AUTO_INCREMENT,
    CONNECTED_WALLET int
        references WALLET,
    NAME              TEXT not null
);

create table if not exists walletmicroservice.TRANSACTIONLIST
(
    ID   INTEGER PRIMARY KEY AUTO_INCREMENT,
    AMOUNT           real not null,
TRANSACTION_TYPE ENUM('CREDIT','DEBIT') NOT NULL,
    TRANSACTION_DATE TEXT not null,
    WALLET_ID       int not null
        references WALLET
);


INSERT INTO walletmicroservice.WALLET (ID, BALANCE) VALUES (1, 50000.0 );
INSERT INTO walletmicroservice.PLAYER (ID, NAME, CONNECTED_WALLET) VALUES (1, "George Malandrakis", 1);
INSERT INTO walletmicroservice.TRANSACTIONLIST(ID, AMOUNT, TRANSACTION_TYPE, TRANSACTION_DATE, WALLET_ID) VALUES (1, 100.0, "DEBIT", "2020-06-03 11:18:05.123" ,1);
INSERT INTO walletmicroservice.TRANSACTIONLIST(ID, AMOUNT, TRANSACTION_TYPE, TRANSACTION_DATE, WALLET_ID) VALUES (2, 100.0, "CREDIT", "2020-06-03 11:18:10.123" ,1);


