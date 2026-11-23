# Generate RSA Key Pair for JWT Authentication

Follow these steps to generate an RSA private/public key pair for JWT authentication.

### 1. Open the terminal in your project directory

Navigate to the root directory of your project:

```bash
cd /path/to/your/project
```

### 2. Create a directory for the RSA keys

Create a `secret` directory and navigate into it:

```bash
mkdir -p secret
cd secret
```

### 3. Generate the RSA private key

Generate a **2048-bit RSA private key**:

```bash
openssl genpkey \
  -algorithm RSA \
  -pkeyopt rsa_keygen_bits:2048 \
  -out private_key.pem
```

The private key will be created at:

```text
secret/private_key.pem
```

### 4. Generate the RSA public key

Generate the public key from the private key:

```bash
openssl pkey \
  -in private_key.pem \
  -pubout \
  -out public_key.pem
```

The public key will be created at:

```text
secret/public_key.pem
```

### 5. Verify the generated keys

To verify the private key:

```bash
openssl pkey -in private_key.pem -text -noout
```

To verify the public key:

```bash
openssl pkey -pubin -in public_key.pem -text -noout
```

### 6. Final project structure

Your project should now have:

```text
your-project/
├── secret/
│   ├── private_key.pem
│   └── public_key.pem
├── src/
├── pom.xml
└── ...
```

### 7. Add the keys to `.gitignore`

The **private key must never be committed to Git**.

Add the following to `.gitignore`:

```gitignore
# JWT RSA keys
../secret/
```

### Important

- **Private key** → Used by the application to **sign JWTs**.
- **Public key** → Used by the application/services to **verify JWT signatures**.
- Never expose or commit `private_key.pem`.
- For production, do not rely on a private key stored directly inside the application repository. Use a secure
  secrets/key-management solution such as AWS Secrets Manager, AWS KMS, HashiCorp Vault, or your cloud provider's
  equivalent.