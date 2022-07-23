import SwiftUI
import shared

struct ContentView: View {
    @State private var image = UIImage()
    @State private var showSheet = false
    
    @State var timeLeft: Int = 30
    @State var onComplete: Bool = true
    @State var recording: Bool = true

    var body: some View {
        NavigationView {
            VStack {
                HStack {
                    Image(uiImage: self.image)
                        .resizable()
                        .cornerRadius(50)
                        .frame(width: 100, height: 100)
                        .background(Color.black.opacity(0.2))
                        .aspectRatio(contentMode: .fill)
                        .clipShape(Circle())

                    Text("Change photo")
                        .font(.headline)
                        .frame(maxWidth: .infinity)
                        .frame(height: 50)
                        .background(LinearGradient(gradient: Gradient(colors: [Color(#colorLiteral(red: 0.262745098, green: 0.0862745098, blue: 0.8588235294, alpha: 1)), Color(#colorLiteral(red: 0.5647058824, green: 0.462745098, blue: 0.9058823529, alpha: 1))]), startPoint: .top, endPoint: .bottom))
                        .cornerRadius(16)
                        .foregroundColor(.white)
                         .padding(.horizontal, 20)
                         .onTapGesture {
                           showSheet = true
                         }
                }
                .padding(.horizontal, 20)
                .sheet(isPresented: $showSheet) {
                    ImagePicker(selectedImage: self.$image)
                }
                
                VStack {
                    NavigationLink(
                        destination: VideoRecordingView(
                            timeLeft: $timeLeft,
                            onComplete: $onComplete,
                            recording: $recording)
                    ) {
                        Text("Record video")
                    }
                }
            }
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
