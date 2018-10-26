module.exports = {
	// database: "mongodb://localhost:27017/cfd",
	// database: "mongodb://safezone:rKsboafHZDbBFJYXr16P9SkfMHH9RBco8EhgLM433jvMIPcKmcu5wxTIePRhKqq5U9biPDnFsxDMMYPMKZgP3g==@safezone.documents.azure.com:10255/mean-dev?ssl=true&sslverifycertificate=false",
	database: process.env.MONGODB_URI || "mongodb://safezone:rKsboafHZDbBFJYXr16P9SkfMHH9RBco8EhgLM433jvMIPcKmcu5wxTIePRhKqq5U9biPDnFsxDMMYPMKZgP3g==@safezone.documents.azure.com:10255/mean?ssl=true&sslverifycertificate=false",
	// database: process.env.MONGODB_URI,
	secret: "secretsecret"
}