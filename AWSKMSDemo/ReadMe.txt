https://www.youtube.com/watch?v=f3APF1dP8w0
https://www.youtube.com/watch?v=e4l-7jzvrao
KMS 
	- used to manage and store encryption keys
	- Encryption - for data in transit and data at rest
	Encrytpion methods for data at-rest
		- Client-side encryption
			Client encrypt the data and manage the keys.
			Use KMS if reqd.
		- Server-side encryption
			AWS encrypts the data and manage the keys
			AWS services like S3, EBS, redshift provides server side encryption and use KMS behind teh scene
	
	- KMS manages Customer Master Keys(CMKs). It doesn't manage Data keys.
	- CMKs can be generated from AWS console/cli/AWS KMS sdk
	- KMS stores CMKs in HSMs(Hardware Security Modules) which is highly secured.
	- CMK keys can only encrypt 4KB of data, for large data we can use Data keys. 
	- Multiple Data keys can be generated from single CMK.
	- It will generated 2 keys - plain data key and encrypted data key. 
	- Plain data key can be used to encrypt the data using openssl or AWS encryption SDK and then this key can be deleted. 
	- Encrypted data key can be stored for later use.
	- KMS API can be called to decrypt the encrypted data key and provide plaintext data key that can be used to decrypt the encrypted text.


AWS KMS + OpenSSL

	1. Create CMK in AWS KMS with alias demo.
	2. Generate Data key from CMK from aws cli(amd prompt).
		aws kms generate-data-key --key-id alias/demo --key-spec AES_256 --region ap-southeast-2 > keys.txt
		It will generate plaintext data key and encrypted version of that.
		These keys are base64 encoded so need to decode it before using.
	3. echo  "<plain text key>" | base64 --decode > datakey
	4. echo  "<encrypted key>" | base64 --decode > encrypted-datakey
	5. Encrypt data using datakey
		$ openssl enc -in ./passwords.txt -out ./passwords-encrypted.txt -e -aes256 -k fileb://./datakey
	6. remove datakey
	7. Call KMS to provide plain text key cprresponding to encrypted key provided
		aws kms decrypt --ciphertext-blob fileb://./encrypted-datakey --region ap-southeast-2
	8. Copy data key
		echo  "<plain text key>" | base64 --decode > datakey
	9. Decrypt the passowrd-encrypted.txt fileb	
		$ openssl enc -in ./passwords-encrypted.txt -out ./passwords-decrypted.txt -d -aes256 -k fileb://./datakey

AWS Encryption SDK + OpenSSL
	3 types of encryption SDKs for client-side encryption
	1. DynamoDB Encryption client
		Use this for DyncamoDB. It will not encrypt the key attributes but encrypt the rest of payload.
	2. S3 encryption client
		Use this to interact with S3.
	3. AWS Encryption SDK
		Can be used in general to encrypt/decrypt the data.
		Data can be encrypted using one language SDK and decrypted using some other language SDK.
	

		