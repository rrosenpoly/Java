class BMI {
    private double weight, height;
  
    final double KILOGRAMS_PER_POUND = 0.45359237;
    final double METERS_PER_INCH = 0.0254;
  
    public BMI(double weight, double height) {
      this.weight = weight;
      this.height = height;
    }
  
    public double getBMI() {
      double weightInKilograms = weight * KILOGRAMS_PER_POUND;
      double heightInMeters = height * METERS_PER_INCH;
      double bmi = weightInKilograms / (heightInMeters * heightInMeters);
      return bmi;
    }
  
    public String getStatus() {
      double bmi = getBMI();
      String status = "Normal";
  
      if (bmi < 18.5)
        status = "Underweight";
      else if (bmi < 25)
        status = "Normal";
      else if (bmi < 30)
        status = "Overweight";
      else
        status = "Obese";
  
      return status;
    }
  }
