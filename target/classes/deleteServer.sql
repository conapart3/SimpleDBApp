CREATE OR REPLACE FUNCTION delete_server(in_id VARCHAR(48))
    RETURNS VOID AS $$
BEGIN

DELETE FROM servers
WHERE id = in_id;

END
$$ LANGUAGE plpgsql