create table clase
(
    claseId     int          not null
        primary key,
    descripcion varchar(200) null,
    constraint CLASE_claseId_uindex
        unique (claseId)
);

create table especie
(
    especieId   float        not null
        primary key,
    claseId     int          null,
    descripcion varchar(200) null,
    constraint ESPECIE_especieId_uindex
        unique (especieId),
    constraint ESPECIE_CLASE_fk
        foreign key (claseId) references clase (claseId)
);

create table recinto
(
    recintoId        int auto_increment
        primary key,
    descripcion      varchar(200) null,
    cantidadAnimales int          null,
    constraint RECINTO_recintoId_uindex
        unique (recintoId)
);

create table tipo_estado
(
    estadoId    int          not null
        primary key,
    descripcion varchar(200) null,
    constraint TIPO_ESTADO_estadoId_uindex
        unique (estadoId)
);

create table animal
(
    animalId  float auto_increment
        primary key,
    especieId float null,
    estadoId  int   null,
    recintoId int   null,
    constraint ANIMAL_animalId_uindex
        unique (animalId),
    constraint ANIMAL_ESPECIE_fk
        foreign key (especieId) references especie (especieId),
    constraint ANIMAL_ESTADO_fk
        foreign key (estadoId) references tipo_estado (estadoId),
    constraint ANIMAL_RECINTO_fk
        foreign key (recintoId) references recinto (recintoId)
);

create table constantes
(
    constanteId float auto_increment
        primary key,
    animalId    float null,
    constante   int   null,
    fecha       date  null,
    constraint CONSTANTES_constanteId_uindex
        unique (constanteId),
    constraint CONSTANTES_ANIMAL_fk
        foreign key (animalId) references animal (animalId)
);

create table estancia
(
    estanciaId    float auto_increment
        primary key,
    animalId      float        null,
    fechaEntrada  date         null,
    fechaSalida   date         null,
    motivoEntrada varchar(200) null,
    constraint estancia_estanciaId_uindex
        unique (estanciaId),
    constraint estancia_ANIMAL_fk
        foreign key (animalId) references animal (animalId)
);

create table tipo_usuario
(
    tipoUsuarioId int         not null
        primary key,
    descripcion   varchar(50) null,
    constraint TIPO_USUARIO_tipoUsuarioId_uindex
        unique (tipoUsuarioId)
);

create table usuario
(
    usuarioId       float auto_increment
        primary key,
    tipoUsuarioId   int          null,
    nombre          varchar(30)  null,
    apellido        varchar(50)  null,
    email           varchar(100) null,
    fechaNacimiento date         null,
    username        varchar(30)  null,
    password        varchar(128) null,
    constraint USUARIO_usuarioId_uindex
        unique (usuarioId),
    constraint USUARIO_TIPOUSUARIO_FK
        foreign key (tipoUsuarioId) references tipo_usuario (tipoUsuarioId)
);

create table avistamiento
(
    avistamientoId float auto_increment
        primary key,
    usuarioId      float        null,
    especieId      float        null,
    descripcion    varchar(200) null,
    fecha          date         null,
    localizacion   varchar(200) null,
    constraint AVISTAMIENTO_avistamientoId_uindex
        unique (avistamientoId),
    constraint AVISTAMIENTO_ESPECIE_fk
        foreign key (especieId) references especie (especieId),
    constraint AVISTAMIENTO_USUARIO_fk
        foreign key (usuarioId) references usuario (usuarioId)
);

create table visita
(
    visitaId float auto_increment
        primary key,
    guia     float not null,
    fecha    date  null,
    constraint visita_visitaId_uindex
        unique (visitaId),
    constraint VISITA_GUIA_FK
        foreign key (guia) references usuario (usuarioId)
);

create table reserva
(
    reservaId        float auto_increment
        primary key,
    usuarioId        float not null,
    visitaId         float not null,
    cantidadPersonas int   null,
    constraint reserva_reservaId_uindex
        unique (reservaId),
    constraint RESERVA_USUARIO_fk
        foreign key (usuarioId) references usuario (usuarioId),
    constraint RESERVA_VISITA_fk
        foreign key (visitaId) references visita (visitaId)
);


