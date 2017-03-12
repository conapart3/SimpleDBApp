CREATE OR REPLACE FUNCTION count_servers()
    RETURNS TABLE (serverCount bigint) AS $$
BEGIN

return query
SELECT COUNT(*) FROM servers;

END
$$ LANGUAGE plpgsql