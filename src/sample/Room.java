package sample;

public class Room {
        private String Id;
        private String Type;
        private String Price;
        private String SleepingPlaceCount;
        private String SilenceLevel;

        public String getId() {
                return Id;
        }

        public void setId(String id) {
                Id = id;
        }

        public String getType() {
                return Type;
        }

        public void setType(String type) {
                Type = type;
        }

        public Room(String type, String price, String sleepingPlaceCount, String silenceLevel) {
                if (type.equals("1"))
                        Type = "Президентский";
                        else if (type.equals("2"))
                                Type = "Люкс";
                        else Type = "Семейный";

                Price = price;
                SleepingPlaceCount = sleepingPlaceCount;
                SilenceLevel = silenceLevel;
        }

        public String getPrice() {
                return Price;
        }

        public void setPrice(String price) {
                Price = price;
        }

        public String getSleepingPlaceCount() {
                return SleepingPlaceCount;
        }

        public void setSleepingPlaceCount(String sleepingPlaceCount) {
                SleepingPlaceCount = sleepingPlaceCount;
        }

        public String getSilenceLevel() {
                return SilenceLevel;
        }

        public void setSilenceLevel(String silenceLevel) {
                SilenceLevel = silenceLevel;
        }

        public Room() {
        }

        public Room(String id, String type, String price, String sleepingPlaceCount, String silenceLevel) {
                Id = id;
                Type = type;
                Price = price;
                SleepingPlaceCount = sleepingPlaceCount;
                SilenceLevel = silenceLevel;
        }
}
