public class Answer extends QandA {

   private int ponctuation;

   public Answer(int ponctuation, String answer) {

      super(answer);
      this.ponctuation = ponctuation;
   }

   public int getPonctuation() {
      return this.ponctuation;
   }

   public String getAnswer() {
      return this.text;
   }
}