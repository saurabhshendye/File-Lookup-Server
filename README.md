# FileLookupServer

This is a simple Client Server based architecture to look up for a file (in linux based system) in a given directory.
Server is equipped to cache files up to 64MB to improve perfromance. Caching is based on the frequency of the of the file being requested.



To run this code:

Step I: run 'make dir'
Step II: 'make clean'
Step III: 'make'
Step IV: Go to 'bin' folder

For Server:
	java endPoints.Server <port Number (> 1024)> <Directory>

For Client:
	java endPoints.Client <Server host> <Server port> <file name> <Directory to store the requested file>
