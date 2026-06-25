library(readxl)
library(tidyr)
library(dplyr)

# Load Data
data_model <- read_excel("C:/Users/lahmy/Desktop/data_model.xlsx", sheet = "50, 60 raw data", col_names = FALSE)
method_names <- data_model[1, ]data_model <- data_model[-1, ]
colnames(data_model) <- method_names

data_model_long <- pivot_longer(data_model,
                                 cols = everything(),
                                 names_to = "Method",
                                 values_to = "Value")data_model_long$Value <- as.numeric(data_model_long$Value)

# Delete Method1
data_model_long <- data_model_long %>% filter(Method != "method1")

# Kruskal-Wallis Testkruskal_test_result <- kruskal.test(Value ~ Method, data = data_model_long)
print(kruskal_test_result)

# Dunn's Testlibrary(dunn.test)dunn_test_result <- dunn.test(data_model_long$Value, data_model_long$Method, method = "bonferroni")

# Plot
library(ggplot2)

ggplot(data_model_long, aes(x = Method, y = Value, fill = Method)) +
  geom_boxplot() +
  theme_minimal() +
  labs(title = "Comparison of Methods", x = "Method", y = "Value")

