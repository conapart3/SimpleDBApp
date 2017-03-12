CREATE OR REPLACE FUNCTION add_server(in_id VARCHAR(48), in_name TEXT, in_description TEXT)
    RETURNS VOID AS $$
BEGIN

INSERT INTO servers(id, name, description)
VALUES(in_id, in_name, in_description);

END
$$ LANGUAGE plpgsql