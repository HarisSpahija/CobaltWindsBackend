insert into auth_roles(auth_role_name) values ('USER'), ('ADMIN'), ('TEAM_CAPTAIN'), ('PLAYER');

insert into users (id, email, password, player_profile_id) VALUES ('admin-uuid', 'admin@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('captain-uuid', 'team_captain@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('player-uuid', 'player@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('user-uuid', 'user@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);

insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('admin-uuid', 'ADMIN');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('admin-uuid', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid', 'TEAM_CAPTAIN');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('player-uuid', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('player-uuid', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('user-uuid', 'USER');

insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('player-playerprofile-uuid', '2023-01-30', 'FALSE', 'Logic', 'https://www.op.gg/summoners/euw/Logic', 4, 5, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('captain-playerprofile-uuid', '2023-01-30', 'FALSE', 'Beedle', 'https://www.op.gg/summoners/euw/Beedle', 2, 3, NULL);

insert into teams (id, biography, creation_date, disband_date, name, open_roles, password, tag, team_captain_id) VALUES ('team1-uuid', 'Team One Gaming, ready to win', '2023-01-30', NULL, 'Team One', NULL, '00000', 'TOG', 'captain-playerprofile-uuid');

UPDATE players SET team_id = 'team1-uuid' WHERE id = 'captain-playerprofile-uuid';
UPDATE players SET team_id = 'team1-uuid' WHERE id = 'player-playerprofile-uuid';

UPDATE users SET player_profile_id = 'player-playerprofile-uuid' WHERE id = 'player-uuid';
UPDATE users SET player_profile_id = 'captain-playerprofile-uuid' WHERE id = 'captain-uuid';

