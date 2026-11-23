## Usage

First, give execution permission:

```
chmod +x scripts/new-common-migration.sh
```

Create migrations:

```
./scripts/new-common-migration.sh create-user-table
```

```
./scripts/new-common-migration.sh add-user-email
```

Generated file:

```
src/main/resources/db/migration/
    V20261122103000__create-user-table.sql
    V20261122103500__add-user-email.sql
```

## **3. Prompt to generate this script**

If you want to use an AI coding assistant to implement it in your project, use this prompt:

Create a new Bash script named `scripts/new-common-migration.sh` for generating Flyway database migration files.

Requirements:

1. Always create migrations inside `src/main/resources/db/migration`.
2. Accept exactly one command-line argument: `<migration-name>`.
3. Generate the migration filename using the format:\
   `V<YYYYMMDDHHMMSS>__<migration-name>.sql`
4. Use the current system timestamp.
5. Validate the migration name to allow only letters, numbers, underscores, and hyphens.
6. Create the target directory if it does not exist.
7. Prevent overwriting an existing migration file.
8. Add a SQL comment containing the generated migration filename.
9. Use `set -e` for error handling.
10. Display a clear success or error message.
11. Provide usage instructions when the argument is missing or invalid.
12. Make the script executable.

Keep the implementation simple, readable, and production-friendly. Do not modify the existing `new-migration.sh` script.

**Recommendation:** Keep both scripts. Use `new-common-migration.sh` for common migrations and your existing `new-migration.sh` for platform and tenant migrations.