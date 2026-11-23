
#!/bin/bash

set -e

if [ "$#" -ne 1 ]; then
    echo "Usage: ./scripts/new-common-migration.sh <migration-name>"
    exit 1
fi

MIGRATION_NAME="$1"
DIRECTORY="src/main/resources/db/migration"

# Validate migration name
if [[ ! "$MIGRATION_NAME" =~ ^[a-zA-Z0-9_-]+$ ]]; then
    echo "ERROR: Migration name can contain only letters, numbers, underscores and hyphens."
    exit 1
fi

TIMESTAMP=$(date +"%Y%m%d%H%M%S")

FILE="${DIRECTORY}/V${TIMESTAMP}__${MIGRATION_NAME}.sql"

if [ -e "$FILE" ]; then
    echo "ERROR: Migration already exists: $FILE"
    exit 1
fi

mkdir -p "$DIRECTORY"

cat > "$FILE" <<EOF
-- Migration: V${TIMESTAMP}__${MIGRATION_NAME}.sql

EOF

echo "Created migration successfully:"
echo "$FILE"
