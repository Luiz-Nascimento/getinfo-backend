$ROOT_USER          = "root"
$NOVO_USUARIO       = "contratos_user"
$BANCO              = "contratosdb"
$CREATE_SQL         = "SQLs/CREATE-TABLE.sql"
$INSERT_SQL         = "SQLs/INSERTS-exemplos.sql"

$SECURE_ROOT_SENHA  = Read-Host -Prompt "Digite a senha do MySQL para o usuário '$ROOT_USER'" -AsSecureString
$BSTR               = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($SECURE_ROOT_SENHA)
$ROOT_PLAIN_SENHA   = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR)

Write-Host "Criando banco '$BANCO'..."
& mysql "-u$ROOT_USER" "-p$ROOT_PLAIN_SENHA" "-e" "CREATE DATABASE IF NOT EXISTS $BANCO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

Write-Host "Criando usuário '$NOVO_USUARIO'..."
& mysql "-u$ROOT_USER" "-p$ROOT_PLAIN_SENHA" "-e" "CREATE USER IF NOT EXISTS '$NOVO_USUARIO'@'localhost' IDENTIFIED BY 'getinfo123';"
& mysql "-u$ROOT_USER" "-p$ROOT_PLAIN_SENHA" "-e" "GRANT ALL PRIVILEGES ON $BANCO.* TO '$NOVO_USUARIO'@'localhost';"
& mysql "-u$ROOT_USER" "-p$ROOT_PLAIN_SENHA" "-e" "FLUSH PRIVILEGES;"

Write-Host "Criando tabelas..."
Get-Content $CREATE_SQL -Raw | & mysql "-u$NOVO_USUARIO" "-pgetinfo123" $BANCO

Write-Host "Importando dados..."
Get-Content $INSERT_SQL  -Raw | & mysql "-u$NOVO_USUARIO" "-pgetinfo123" $BANCO

Write-Host "Concluído com sucesso!"
