drop database if exists orbiters_test;
create database orbiters_test;
use orbiters_test;


create table orbiter (
    orbiter_id int primary key auto_increment,
    `name` varchar(50),
    `type` varchar(50),
    sponsor varchar(50) 
);


delimiter //
create procedure set_known_good_state()
begin
    -- 2. Throws out all records without executing deletes.
    -- Resets the auto_increment value.
    truncate table orbiter;

    -- 3. Add test data.
    insert into orbiter
        (`name`, `type`, sponsor)
    values
        ('Carmen','MODULE', 'Tesla'),
        ('Julia', 'ASTRONAUT', 'Ford'),
        ('Katherine', 'SHUTTLE', 'NASA');
        
end //
-- 4. Change the statement terminator back to the original.
delimiter ;