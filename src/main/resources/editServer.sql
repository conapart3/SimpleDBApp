CREATE OR REPLACE FUNCTION edit_server(in_id integer, in_name VARCHAR(20), in_description TEXT)
    RETURNS VOID AS $$
BEGIN

UPDATE servers
SET name = in_name, description = in_description
WHERE id = in_id;

END
$$ LANGUAGE plpgsql