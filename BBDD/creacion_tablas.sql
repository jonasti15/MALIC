create table clase
(
    clase_id    int          not null
        primary key,
    descripcion varchar(200) null,
    constraint CLASE_claseId_uindex
        unique (clase_id)
);

create table especie
(
    especie_id  float        not null
        primary key,
    clase_id    int          null,
    descripcion varchar(200) null,
    constraint ESPECIE_especieId_uindex
        unique (especie_id),
    constraint ESPECIE_CLASE_fk
        foreign key (clase_id) references clase (clase_id)
);

create table recinto
(
    recinto_id        int auto_increment
        primary key,
    descripcion       varchar(200) null,
    cantidad_animales int          null,
    constraint RECINTO_recintoId_uindex
        unique (recinto_id)
);

create table tipo_estado
(
    estado_id   int          not null
        primary key,
    descripcion varchar(200) null,
    constraint TIPO_ESTADO_estadoId_uindex
        unique (estado_id)
);

create table animal
(
    animal_id  float auto_increment
        primary key,
    especie_id float null,
    estado_id  int   null,
    recinto_id int   null,
    constraint ANIMAL_animalId_uindex
        unique (animal_id),
    constraint ANIMAL_ESPECIE_fk
        foreign key (especie_id) references especie (especie_id),
    constraint ANIMAL_ESTADO_fk
        foreign key (estado_id) references tipo_estado (estado_id),
    constraint ANIMAL_RECINTO_fk
        foreign key (recinto_id) references recinto (recinto_id)
);

create table constantes
(
    constante_id float auto_increment
        primary key,
    animal_id    float null,
    constante    int   null,
    fecha        date  null,
    constraint CONSTANTES_constanteId_uindex
        unique (constante_id),
    constraint CONSTANTES_ANIMAL_fk
        foreign key (animal_id) references animal (animal_id)
);

create table estancia
(
    estancia_id    float auto_increment
        primary key,
    animal_id      float        null,
    fecha_entrada  date         null,
    fecha_salida   date         null,
    motivo_entrada varchar(200) null,
    constraint estancia_estanciaId_uindex
        unique (estancia_id),
    constraint estancia_ANIMAL_fk
        foreign key (animal_id) references animal (animal_id)
);

create table tipo_usuario
(
    tipo_usuario_id int         not null
        primary key,
    descripcion     varchar(50) null,
    constraint TIPO_USUARIO_tipoUsuarioId_uindex
        unique (tipo_usuario_id)
);

create table usuario
(
    usuario_id       float auto_increment
        primary key,
    tipo_usuario_id  int          null,
    nombre           varchar(30)  null,
    apellido         varchar(50)  null,
    email            varchar(100) null,
    fecha_nacimiento date         null,
    username         varchar(30)  null,
    password         varchar(128) null,
    constraint USUARIO_usuarioId_uindex
        unique (usuario_id),
    constraint USUARIO_TIPOUSUARIO_FK
        foreign key (tipo_usuario_id) references tipo_usuario (tipo_usuario_id),
    constraint FKe581tp719p3d7o5u2w9sre10b
        foreign key (tipo_usuario_id) references tipo_usuario (tipo_usuario_id)
);

create table avistamiento
(
    avistamiento_id float auto_increment
        primary key,
    usuario_id      float        null,
    especie_id      float        null,
    descripcion     varchar(200) null,
    fecha           date         null,
    localizacion    varchar(200) null,
    constraint AVISTAMIENTO_avistamientoId_uindex
        unique (avistamiento_id),
    constraint AVISTAMIENTO_ESPECIE_fk
        foreign key (especie_id) references especie (especie_id),
    constraint AVISTAMIENTO_USUARIO_fk
        foreign key (usuario_id) references usuario (usuario_id)
);

create table visita
(
    visita_id float auto_increment
        primary key,
    guia      float not null,
    fecha     date  null,
    constraint visita_visitaId_uindex
        unique (visita_id),
    constraint VISITA_GUIA_FK
        foreign key (guia) references usuario (usuario_id)
);

create table reserva
(
    reserva_id        float auto_increment
        primary key,
    usuario_id        float not null,
    visita_id         float not null,
    cantidad_personas int   null,
    constraint reserva_reservaId_uindex
        unique (reserva_id),
    constraint RESERVA_USUARIO_fk
        foreign key (usuario_id) references usuario (usuario_id),
    constraint RESERVA_VISITA_fk
        foreign key (visita_id) references visita (visita_id)
);

