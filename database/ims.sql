create table investors
(
    cpf      int auto_increment
        primary key,
    name     varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255) not null
);

create table portfolios
(
    id            int auto_increment
        primary key,
    total_balance double not null,
    profitability double null,
    investor_cpf  int    null,
    constraint portfolios_ibfk_1
        foreign key (investor_cpf) references investors (cpf)
);

create table assets
(
    id           int auto_increment
        primary key,
    name         varchar(255) not null,
    price        double       not null,
    portfolio_id int          null,
    constraint assets_ibfk_1
        foreign key (portfolio_id) references portfolios (id)
);

create index portfolio_id
    on assets (portfolio_id);

create table bonds
(
    id            int auto_increment
        primary key,
    name          varchar(255) not null,
    price         double       not null,
    interest_rate double       not null,
    duration      int          not null,
    issuer        varchar(255) not null,
    credit_rating varchar(10)  not null,
    asset_id      int          null,
    constraint bonds_ibfk_1
        foreign key (asset_id) references assets (id)
);

create index asset_id
    on bonds (asset_id);

create table funds
(
    id                     int auto_increment
        primary key,
    name                   varchar(255) not null,
    price                  double       not null,
    type                   varchar(255) not null,
    management_fee         double       not null,
    manager                varchar(255) not null,
    historical_performance double       not null,
    asset_id               int          null,
    constraint funds_ibfk_1
        foreign key (asset_id) references assets (id)
);

create index asset_id
    on funds (asset_id);

create table portfolio_has_assets
(
    portfolio_id int not null,
    asset_id     int not null,
    primary key (portfolio_id, asset_id),
    constraint portfolio_has_assets_ibfk_1
        foreign key (portfolio_id) references portfolios (id),
    constraint portfolio_has_assets_ibfk_2
        foreign key (asset_id) references assets (id)
);

create index asset_id
    on portfolio_has_assets (asset_id);

create index investor_id
    on portfolios (investor_cpf);

create table stocks
(
    id                 int auto_increment
        primary key,
    name               varchar(255) not null,
    price              double       not null,
    num_shares         int          not null,
    dividend_per_share double       not null,
    market_cap         double       not null,
    industry_sector    varchar(255) not null,
    asset_id           int          null,
    constraint stocks_ibfk_1
        foreign key (asset_id) references assets (id)
);

create index asset_id
    on stocks (asset_id);


