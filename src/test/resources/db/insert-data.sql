insert into Identity(uuid, name, surname, login, email, age, encodedPassword) values('e0f899cf-3c3d-490f-991d-6a545feb6de9','Fero', 'Taraba','ferko', 'ferko@taraba.sk', 35,'password');
insert into Role(uuid, name, description, level, identity_uuid) values('e0f899cf-3c3d-490f-991d-6a544feb6de9','admin', 'administratorska rola', 8, 'e0f899cf-3c3d-490f-991d-6a545feb6de9');