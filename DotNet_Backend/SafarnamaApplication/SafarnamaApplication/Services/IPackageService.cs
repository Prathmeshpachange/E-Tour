using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.Services
{
    public interface IPackageService
    {
        Task<IEnumerable<PackageDTO>> GetAllPackages();
        Task<PackageDTO?> GetPackageById(int id);
        Task AddPackage(PackageDTO packageDto);
        Task UpdatePackage(PackageDTO packageDto);
        Task DeletePackage(int id);
        Task<bool> PackageExists(int id);

        Task<IEnumerable<PackageDTO>> GetPackagesBySubcategoryIdAsync(int subcategoryId);


    }
}
