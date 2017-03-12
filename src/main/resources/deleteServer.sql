CREATE OR REPLACE FUNCTION delete_server(in_id integer)
    RETURNS VOID AS $$
BEGIN

DELETE FROM servers
WHERE id = in_id;

END
$$ LANGUAGE plpgsql