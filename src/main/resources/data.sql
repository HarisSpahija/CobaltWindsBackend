insert into auth_roles(auth_role_name) values ('USER'), ('ADMIN'), ('TEAM_CAPTAIN'), ('PLAYER');

insert into users (id, email, password, player_profile_id) VALUES ('admin-uuid', 'admin@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('captain-uuid-1', 'team_captain1@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('captain-uuid-2', 'team_captain2@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('captain-uuid-3', 'team_captain3@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-1', 'team_member1@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-2', 'team_member2@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-3', 'team_member3@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-4', 'team_member4@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-5', 'team_member5@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-6', 'team_member6@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-7', 'team_member7@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-8', 'team_member8@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-9', 'team_member9@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-10', 'team_member10@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-11', 'team_member11@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('team_member-uuid-12', 'team_member12@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('free_agent-uuid', 'free_agent1@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);
insert into users (id, email, password, player_profile_id) VALUES ('user-uuid', 'user1@cwl.com', '$2a$10$JiIODQ79Htchfe27OYtS3ORo6lYmJh4qEevbs0J.vsqIf.qKgQiNC', null);

-- Set Admin Roles --
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('admin-uuid', 'ADMIN');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('admin-uuid', 'USER');
-- Set Captains Roles--
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-1', 'TEAM_CAPTAIN');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-1', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-1', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-2', 'TEAM_CAPTAIN');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-2', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-2', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-3', 'TEAM_CAPTAIN');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-3', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('captain-uuid-3', 'USER');
-- Set Players Roles --
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-1', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-1', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-2', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-2', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-3', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-3', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-4', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-4', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-5', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-5', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-6', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-6', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-7', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-7', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-8', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-8', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-9', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-9', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-10', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-10', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-11', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-11', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-12', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('team_member-uuid-12', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('free_agent-uuid', 'PLAYER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('free_agent-uuid', 'USER');
insert into users_auth_roles (users_id, auth_roles_auth_role_name) VALUES ('user-uuid', 'USER');

-- Team REEF ESPORTS --
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('captain-uuid-1', '2023-01-30', 'FALSE', 'RE Westman', 'https://www.op.gg/summoners/euw/RE%20Westman', 2, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-1', '2023-01-30', 'FALSE', 'RE Smette', 'https://www.op.gg/summoners/euw/RE%20Smette', 3, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-2', '2023-01-30', 'FALSE', 'Addi', 'https://www.op.gg/summoners/euw/Addi', 4, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-3', '2023-01-30', 'FALSE', 'Oceancroc', 'https://www.op.gg/summoners/eune/Oceancroc', 5, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-4', '2023-01-30', 'FALSE', 'RE Webbiieeee', 'https://www.op.gg/summoners/euw/RE%20Webbiieeee', 6, NULL, NULL);

-- Team SG Clarity --
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('captain-uuid-2', '2023-01-30', 'FALSE', '2h tst pepega', 'https://www.op.gg/summoners/euw/2h%20tst%20pepega', 2, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-5', '2023-01-30', 'FALSE', 'Hattune', 'https://www.op.gg/summoners/euw/Hattune', 3, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-6', '2023-01-30', 'FALSE', 'Fozzmaniac', 'https://www.op.gg/summoners/euw/Fozzmaniac', 4, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-7', '2023-01-30', 'FALSE', 'Alvance Pro', 'https://www.op.gg/summoners/euw/Alvance%20Pro', 5, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-8', '2023-01-30', 'FALSE', 'Absolutely Halal', 'https://www.op.gg/summoners/euw/Absolutely%20Halal', 6, NULL, NULL);

-- Team Abstract Chaos --
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('captain-uuid-3', '2023-01-30', 'FALSE', 'Railthemail', 'https://www.op.gg/summoners/euw/Railthemail', 2, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-9', '2023-01-30', 'FALSE', 'shy tiny girl', 'https://www.op.gg/summoners/na/shy%20tiny%20girl', 3, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-10', '2023-01-30', 'FALSE', 'King Arsenic', 'https://www.op.gg/summoners/euw/King%20Arsenic', 4, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-11', '2023-01-30', 'FALSE', 'BloodBoy', 'https://www.op.gg/summoners/euw/BloodBoy', 5, NULL, NULL);
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('team_member-uuid-12', '2023-01-30', 'FALSE', 'FIay', 'https://www.op.gg/summoners/euw/FIay', 6, NULL, NULL);

-- FREE AGENTS --
insert into players (id, date_of_birth, free_agent, name, opgg_link, primary_role, secondary_role, team_id) VALUES ('free_agent-uuid', '2023-01-30', 'TRUE', 'Beedle', 'https://www.op.gg/summoners/euw/Beedle', 2, 3, NULL);

-- CREATE TEAMS --
insert into teams (id, biography, creation_date, disband_date, name, open_roles, password, tag, team_captain_id) VALUES ('team-uuid-1', 'REEF ESPORTS, we are here to win!', '2023-01-30', NULL, 'REEF ESPORTS', NULL, '00000', 'REE', 'captain-uuid-1');
insert into teams (id, biography, creation_date, disband_date, name, open_roles, password, tag, team_captain_id) VALUES ('team-uuid-2', 'SG Clarity are you ready for our clarity?', '2023-01-30', NULL, 'SG Clarity', NULL, '00000', 'SGC', 'captain-uuid-2');
insert into teams (id, biography, creation_date, disband_date, name, open_roles, password, tag, team_captain_id) VALUES ('team-uuid-3', 'Abstract Chaos is gonna win it all', '2023-01-30', NULL, 'Abstract Chaos', NULL, '00000', 'ASC', 'captain-uuid-3');

-- Create Relation between Team and Player --
UPDATE players SET team_id = 'team-uuid-1' WHERE id = 'captain-uuid-1';
UPDATE players SET team_id = 'team-uuid-1' WHERE id = 'team_member-uuid-1';
UPDATE players SET team_id = 'team-uuid-1' WHERE id = 'team_member-uuid-2';
UPDATE players SET team_id = 'team-uuid-1' WHERE id = 'team_member-uuid-3';
UPDATE players SET team_id = 'team-uuid-1' WHERE id = 'team_member-uuid-4';

UPDATE players SET team_id = 'team-uuid-2' WHERE id = 'captain-uuid-2';
UPDATE players SET team_id = 'team-uuid-2' WHERE id = 'team_member-uuid-5';
UPDATE players SET team_id = 'team-uuid-2' WHERE id = 'team_member-uuid-6';
UPDATE players SET team_id = 'team-uuid-2' WHERE id = 'team_member-uuid-7';
UPDATE players SET team_id = 'team-uuid-2' WHERE id = 'team_member-uuid-8';

UPDATE players SET team_id = 'team-uuid-3' WHERE id = 'captain-uuid-3';
UPDATE players SET team_id = 'team-uuid-3' WHERE id = 'team_member-uuid-9';
UPDATE players SET team_id = 'team-uuid-3' WHERE id = 'team_member-uuid-10';
UPDATE players SET team_id = 'team-uuid-3' WHERE id = 'team_member-uuid-11';
UPDATE players SET team_id = 'team-uuid-3' WHERE id = 'team_member-uuid-12';

-- Create Relation between User and Player --
UPDATE users SET player_profile_id = 'captain-uuid-1' WHERE id = 'captain-uuid-1';
UPDATE users SET player_profile_id = 'captain-uuid-2' WHERE id = 'captain-uuid-3';
UPDATE users SET player_profile_id = 'captain-uuid-3' WHERE id = 'captain-uuid-4';
UPDATE users SET player_profile_id = 'team_member-uuid-1' WHERE id = 'team_member-uuid-1';
UPDATE users SET player_profile_id = 'team_member-uuid-2' WHERE id = 'team_member-uuid-2';
UPDATE users SET player_profile_id = 'team_member-uuid-3' WHERE id = 'team_member-uuid-3';
UPDATE users SET player_profile_id = 'team_member-uuid-4' WHERE id = 'team_member-uuid-4';
UPDATE users SET player_profile_id = 'team_member-uuid-5' WHERE id = 'team_member-uuid-5';
UPDATE users SET player_profile_id = 'team_member-uuid-6' WHERE id = 'team_member-uuid-6';
UPDATE users SET player_profile_id = 'team_member-uuid-7' WHERE id = 'team_member-uuid-7';
UPDATE users SET player_profile_id = 'team_member-uuid-8' WHERE id = 'team_member-uuid-8';
UPDATE users SET player_profile_id = 'team_member-uuid-9' WHERE id = 'team_member-uuid-9';
UPDATE users SET player_profile_id = 'team_member-uuid-10' WHERE id = 'team_member-uuid-10';
UPDATE users SET player_profile_id = 'team_member-uuid-11' WHERE id = 'team_member-uuid-11';
UPDATE users SET player_profile_id = 'team_member-uuid-12' WHERE id = 'team_member-uuid-12';
UPDATE users SET player_profile_id = 'team_member-uuid-1' WHERE id = 'team_member-uuid-1';
UPDATE users SET player_profile_id = 'free_agent-uuid' WHERE id = 'free_agent-uuid';

