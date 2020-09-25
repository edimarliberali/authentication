
# Spring security with authentication and authorization in different tables

 This example explains how authentication ana Authorization works in different tables using two resources.
 
## Script

CREATE TABLE public.usuario_gestao (
  id numeric(18,0) NOT NULL,
  name character varying(65) NOT NULL,
  username character varying(65) NOT NULL,
  password character varying(64) NOT NULL,
  id_role numeric(18,0) NOT NULL
);

CREATE TABLE public.usuario_portal (
  id numeric(18,0) NOT NULL,
  name character varying(65) NOT NULL,
  username character varying(65) NOT NULL,
  password character varying(64) NOT NULL,
  id_role numeric(18,0) NOT NULL
)

## Program arguments
--spring.profiles.active=development
