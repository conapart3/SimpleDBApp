CREATE OR REPLACE FUNCTION add_server(in_name VARCHAR(20), in_description TEXT)
    RETURNS VOID AS $$
BEGIN

INSERT INTO servers(name, description)
VALUES(in_name, in_description);

END
$$ LANGUAGE plpgsql